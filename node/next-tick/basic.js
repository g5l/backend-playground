// process.nextTick POC
// Demonstrates how nextTick defers execution until the end of the current
// operation, before the event loop continues (before I/O, timers, etc.)

console.log("1 - sync: start");

process.nextTick(() => {
  console.log("3 - nextTick: runs after current op, before I/O");
});

process.nextTick(() => {
  console.log("4 - nextTick: second callback (FIFO order)");
  process.nextTick(() => {
    console.log("5 - nextTick: nested — still drains before I/O");
  });
});

setImmediate(() => {
  console.log("6 - setImmediate: check phase (after I/O)");
});

setTimeout(() => {
  console.log("7 - setTimeout: timers phase");
}, 0);

Promise.resolve().then(() => {
  console.log("8 - Promise.then: microtask, runs after nextTick queue");
});

console.log("2 - sync: end");

// Expected output order:
// 1 - sync: start
// 2 - sync: end
// 3 - nextTick: runs after current op, before I/O
// 4 - nextTick: second callback (FIFO order)
// 5 - nextTick: nested — still drains before I/O
// 8 - Promise.then: microtask, runs after nextTick queue
// 6 - setImmediate: check phase (after I/O)   <- order of 6 & 7 may vary
// 7 - setTimeout: timers phase
