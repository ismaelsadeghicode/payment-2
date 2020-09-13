import React from 'react';

import axios from 'axios';
import
{Router, Route, Switch}
    from "react-router";
import AppHeader from "./common/AppHeader";
import Login from "./user/login/Login";
import {getCurrentUser} from './util/APIUtils';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';
import './App.css';
import Report from './rport/Report';

import NameForm from './rport/NameForm';
import Active from './rport/Active';
import Remain from './rport/Remain';
import allreport from './rport/allreport';
import Counter from './rport/counter';

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            persons: [],
            authenticated: false,
            currentUser: null,
            loading: false
        }

    }

    loadCurrentlyLoggedInUser() {
        this.setState({
            loading: true
        });

        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    authenticated: true,
                    loading: false
                });
            }).catch(error => {
            this.setState({
                loading: false
            });
        });
    }

    componentDidMount() {
        this.loadCurrentlyLoggedInUser();
    }


    render() {

        return (



            <div className="app">

                {/*برای هدر استفاده می شود*/}
                {/*<div className="app-top-box">*/}
                {/*    <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout} />*/}
                {/*</div>*/}
                <div className="app-body">
                    <Switch>

                        <Route exact path="/allreport" component={allreport}></Route>
                        <Route exact path="/NameForm" component={NameForm}/>
                        <Route exact path="/Active" component={Active}/>
                        <Route exact path="/Remain" component={Remain}/>
                        <Route exact path="/operator" component={Counter}></Route>

                        <Route path="/"
                               render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
                    </Switch>
                </div>
                <Alert stack={{limit: 3}}
                       timeout = {3000}
                       position='top-right' effect='slide' offset={65} />
            </div>

        );
    }
}
