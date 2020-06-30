console.log("evil audience :-)");

const socket = new WebSocket("ws://localhost:8080/jtt/notifications");
socket.onopen = e => console.log('opening a socket', e);
window.setInterval(_ => socket.send("hello from js " + new Date()), 2000);


socket.onmessage = e => console.log("message back from server", e.data);