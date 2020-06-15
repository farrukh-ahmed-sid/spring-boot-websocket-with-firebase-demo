importScripts("https://www.gstatic.com/firebasejs/7.15.1/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/7.15.1/firebase-messaging.js");

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