// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyDmiIQJVXO4FYw43OmJtPCQKVp5QRRuz80",
    authDomain: "fir-push-notification-88ac4.firebaseapp.com",
    databaseURL: "https://fir-push-notification-88ac4.firebaseio.com",
    projectId: "fir-push-notification-88ac4",
    storageBucket: "fir-push-notification-88ac4.appspot.com",
    messagingSenderId: "77731529146",
    appId: "1:77731529146:web:8ead6275a5d1c554eedd83"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

//Get access to all messaging services
const messaging = firebase.messaging();

messaging.requestPermission()
.then(function(){
    console.log("Have Permissions");
    return messaging.getToken();
})
.then(function(token){
    console.log("token: " + token);
})
.catch(function(err){
    console.log("Error occur");
})

messaging.onMessage(function(payload){
    console.log(payload);
});

//WebSocket configurations
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}

function connect() {
    var socket = new SockJS('/websocket-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#userinfo").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});