
console.log("1 - sync: start");

process.nextTick(() => {
  console.log("3 - nextTick: runs after current op, before I/O");
});

process.nextTick(() => {
  console.log("4 - nextTick: second callback (FIFO order)");
  process.nextTick(() => {
    console.log("5 - nextTick: nested still drains before I/O");
  });
});

console.log("2 - sync: end");
