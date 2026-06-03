function flushTelemetry(timeoutMs) {
  return new Promise(resolve => {
    console.log(`flushing telemetry (max ${timeoutMs}ms)...`);
    setTimeout(() => {
      console.log('telemetry flushed');
      resolve();
    }, Math.min(500, timeoutMs));
  });
}

process.on('uncaughtExceptionMonitor', (err, origin) => {
  console.error('crash imminent:', err, 'origin:', origin);
});

process.on('uncaughtException', async (err, origin) => {
  console.error('uncaught:', err);
  try {
    await flushTelemetry(2000);
  } finally {
    process.exit(1);
  }
});

setTimeout(() => {
  throw new Error('something went horribly wrong');
}, 100);
