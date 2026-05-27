# Node.js Lifecycle Events

Node.js lifecycle events refer to the sequence of stages a program goes through from the moment you run a command like `node app.js` to the final exit of the process. This cycle is driven primarily by the Event Loop, which keeps the application running as long as there is work to do.

## 1. Process Startup (Bootstrap)

Before your code even runs, the Node.js executable performs several internal setup steps:

- **Initialization:** It parses command-line flags, initializes the V8 engine, and sets up libuv (the library that manages the event loop).
- **Loading:** It registers native modules and runs internal bootstrap JavaScript.
- **Execution:** It loads and executes your entry file (e.g., `index.js`). All variables and functions are registered in memory during this phase.

## 2. The Running Phase (Event Loop)

Once the initial script is executed, Node enters the event loop. The loop continues to run in "ticks" as long as there are active listeners, timers, or open network connections.

- **Poll Phase:** The loop waits for new I/O events (like incoming HTTP requests).
- **Check Phase:** Executes `setImmediate()` callbacks.
- **Close Callbacks:** Handles events like `socket.on('close', ...)`.

## 3. Termination and Graceful Shutdown

The process exits when there are no more callbacks to perform or when it receives a termination signal.

- **Signals:** Common signals include `SIGINT` (Ctrl+C) or `SIGTERM` (used by platforms like Kubernetes to stop a container).
- **Exit Codes:** The process closes with an exit code (e.g., `0` for success).
- **Cleanup:** Developers often hook into these events to close database connections or finish writing logs before the process completely dies.

### Files in this project

| File | What it demonstrates |
|---|---|
| `before-exit-vs-exit.js` | Difference between `beforeExit` and `exit` events, and why async work inside `beforeExit` loops forever |
| `graceful-shutdown.js` | Handling `SIGINT`/`SIGTERM` to drain in-flight HTTP requests before exiting |

#### `before-exit-vs-exit.js`

This file teaches you the difference between two process events:

**`exit`** fires at the very last millisecond before Node dies. At that point it is too late to do anything async (like a timer or a network call). The `setTimeout` on line 8 is scheduled but Node is already gone before it can run, so `"never"` is literally never printed.

**`beforeExit`** fires when the event loop runs out of things to do but before the process actually closes. Because you are still alive here, you can schedule async work. The `setTimeout` on line 3 adds new work to the queue, which means the event loop is no longer empty, so `beforeExit` fires again... and again. If you are not careful, this creates an infinite loop.

The lesson: use `beforeExit` only for cleanup that must be async. Use `exit` only for synchronous last words (like a final log line). Never try to do async work inside `exit`.

## Framework-Specific Lifecycles

If you are using a framework, they often provide higher-level hooks for these events:

- **NestJS:** Provides hooks like `onModuleInit`, `onApplicationBootstrap`, and `onModuleDestroy` to manage service lifecycles.
- **LoopBack:** Uses `boot()`, `init()`, `start()`, and `stop()` to control application state.
- **ROS 2:** Uses managed nodes with states like `configuring`, `active`, `deactivating`, and `shuttingDown`.
