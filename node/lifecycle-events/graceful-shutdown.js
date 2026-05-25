import http from 'node:http';

const server = http.createServer(async (req, res) => {
  await new Promise(r => setTimeout(r, 2000));
  res.end('ok');
});

server.listen(3000);

let shuttingDown = false;

async function shutdown(signal) {
  if (shuttingDown) return;
  shuttingDown = true;
  console.log(`\n${signal} received, draining...`);

  server.close(err => {
    if (err) console.error('close error:', err);
    else console.log('server closed cleanly');
  });

  const forceExit = setTimeout(() => {
    console.error('drain timeout, forcing exit');
    process.exit(1);
  }, 10_000);
  forceExit.unref();
  
}

process.on('SIGTERM', () => shutdown('SIGTERM'));
process.on('SIGINT',  () => shutdown('SIGINT'));
