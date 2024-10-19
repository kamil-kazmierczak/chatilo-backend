import React, {useEffect, useState} from 'react';
import "./App.css";
import Login from "./components/Login";
import UserService from "./services/user-service";
import AuthenticationService from "./services/authentication-service";
import {Client as WebSocketClient} from '@stomp/stompjs';

const SOCKET_URL = 'ws://localhost:8081/chatilo-websocket'
const TOPIC_NAME = '/topic/message'

const App = () => {
    const [messages, setMessages] = useState([]);
    const [loggedIn, isLoggedIn] = useState(false);
    const [loggedUser, setLoggedUser] = useState(undefined);
    const [wsClient, setWsClient] = useState(undefined);

    useEffect(() => {
        const input = document.getElementById("inputMessage");
        if (input) {
            input.addEventListener("keypress", (event) => {
                if (event.key === 'Enter') {
                    document.getElementById('inputButton').click();
                    clearInput()
                }
            })
        }

        const scrollingElement = document.getElementById("messages-area");
        const config = {childList: true};
        const callback = (mutationsList, observer) => {
            for (let mutation of mutationsList) {
                if (mutation.type === "childList") {
                    window.scrollTo(0, document.body.scrollHeight);
                }
            }
        };

        const observer = new MutationObserver(callback);
        observer.observe(scrollingElement, config);

        if (AuthenticationService.isUserLoggedIn()) {
            AuthenticationService.addAuthorizationHeaderToRequests()
        }
    })

    useEffect(() => {
        if (AuthenticationService.isUserLoggedIn()) {
            UserService.getLoggedUser().then(response => setLoggedUser(response.data));
        }
    }, [loggedIn])

    useEffect(() => {
        const onConnected = () => {
            console.log("Connected!!")
            wsClient.subscribe(TOPIC_NAME, onMessageReceived);
        }

        const onDisconnected = () => console.log("Disconnected!!")

        const wsClient = new WebSocketClient({
            brokerURL: SOCKET_URL,
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
            onConnect: onConnected,
            onDisconnect: onDisconnected,
        });

        wsClient.activate();

        wsClient.debug = (msg) => console.log('WS - Debug: ', msg);

        setWsClient(wsClient);
    }, [])

    const onMessageReceived = (msg) => {
        console.log('On message received: ', msg.body);
        setMessages(prevState => [...prevState, JSON.parse(msg.body)]);
    }

    const clearInput = () => {
        document.getElementById("inputMessage").value = '';
    }

    const sendMessage = () => {
        const msg = document.getElementById("inputMessage").value.trim();
        var userName = AuthenticationService.getLoggedInUserName();
        console.log("loggedUser: ",  userName)

        if (msg === '') {
            return;
        }

        wsClient.publish({
            destination: "/app/all", body: JSON.stringify({
                message: msg,
                from: userName
            })
        });

    }

    return (

        <div id="messages-area" className="pt-6 mt-6 mr-3 ml-3 pb-6 mb-6">
            {
                messages.map(message => (
                        <div className="message-field custom-font">
                            <div>{message.from}</div>
                            <div>{message.message}</div>
                        </div>
                    )
                )
            }

            {!loggedIn && !loggedUser &&
                <Login isLoggedIn={isLoggedIn}/>
            }

            {loggedUser && (
                <nav className="navbar navbar-border-bottom is-fixed-top p-2">
                    <div className="navbar-menu">
                        <div className="navbar-start">
                            <div className="navbar-item is-size-3">
                                <strong>Chatilo</strong>
                            </div>
                        </div>

                        <div className="navbar-end">
                            <div className="navbar-item">
                                <strong>{loggedUser.email}</strong>
                            </div>
                            <div className="navbar-item">
                                <div className="buttons">
                                    <a className="button is-danger is-light" id="logout"
                                       onClick={<AuthenticationService> </AuthenticationService>.logout}>
                                        <strong>Logout</strong>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>)
            }

            {loggedUser &&
                <div id="send-area" className="p-5">
                    <input className="input" type="text" id="inputMessage" name="inputMessage"/>
                    <button className="button is-primary" id="inputButton" onClick={sendMessage}>Send &#9166;</button>
                </div>
            }
        </div>
    )
}

export default App;
