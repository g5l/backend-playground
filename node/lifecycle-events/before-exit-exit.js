process.on('beforeExit', (code) => {
  console.log('beforeExit:', code);
  setTimeout(() => console.log('still running'), 100);
});

process.on('exit', (code) => {
  console.log('exit:', code);
  setTimeout(() => console.log('never'), 0);
});

console.log('main done');
