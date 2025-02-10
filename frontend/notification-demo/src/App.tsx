import './App.css'
import {useEffect} from "react";
import {messaging} from "./firebase.ts";
import {toast, ToastContainer} from "react-toastify";
import {getToken, onMessage} from "firebase/messaging";


function Message({notification}: { notification: any }) {
    console.log(`Printing the message ${JSON.stringify(notification)} `)
    return (
        <>
            <div id="notificationHeader">
                {notification.image && (
                    <div id="imageContainer">
                        <img src={notification.image} width={100}/>
                    </div>
                )}
                <span>{notification.title}</span>
            </div>
            <div id="notificationBody">{notification.body}</div>
        </>
    );
}

function App() {
    async function requestPermission() {

        const permission = await Notification.requestPermission();
        if (permission === "granted") {
            try {
                const token = await getToken(messaging, {
                    vapidKey: import.meta.env.VITE_FIREBASE_VAPID_KEY,
                });
                console.log("this token is needed for server side push notifications: ", token);
            } catch (e) {
                console.log("Error generating token : ", e);
            }
        } else if (permission === "denied") {
            //notifications are blocked
            alert("You denied for the notification");
        }
    }

    useEffect(() => {
        requestPermission();
    }, []);

    onMessage(messaging, (payload) => {
        toast(() => <Message notification={payload.notification}/>);
    });
    return (
        <div className="App">
            <h1>Firebase Push Notifications</h1>
            <ToastContainer/>
        </div>
    );
}

export default App
