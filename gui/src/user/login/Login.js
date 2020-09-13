import React, {Component, PropTypes} from 'react'

import './Login.css';
import axios from "axios";
import logo from '../../img/logo-new.png';

class Login extends Component {
    constructor() {
        super();


        this.state = {
            auth: [],
            username: '',
            password: '',
            error: '',
        };

        this.handlePassChange = this.handlePassChange.bind(this);
        this.handleUserChange = this.handleUserChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.dismissError = this.dismissError.bind(this);
    }

    dismissError() {
        this.setState({error: ''});
    }

    handleSubmit(evt) {
        evt.preventDefault();

        if (!this.state.username) {
            return this.setState({error: 'Username is required'});
        }

        if (!this.state.password) {
            return this.setState({error: 'Password is required'});
        }

        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Basic cGFyc2lhbl90b3B1cDokPFBAUlNJQU46Pjw6dG9wdXA+IQ==',
        };

        axios.post('http://192.168.50.150:8080/api/v1/login', {
            username: this.state.username,
            password: this.state.password
        }, {headers})
            .then(res => {
                const auth = res.data;
                this.setState({auth: auth});
                console.log(this.state.auth.successful)
                if (this.state.auth.successful === true) {
                    if (this.state.username === "admin" && this.state.password === "admin") {
                        localStorage.setItem('key', 'مدیرسامانه');
                        window.sessionStorage.setItem('key1', 'true');
                        return this.props.history.push('/operator');
                    } else if (this.state.username === "user" && this.state.password === "user") {
                        localStorage.setItem('key', 'کاربر سامانه');
                        window.sessionStorage.setItem('key1', 'true');
                        return this.props.history.push('/operator');
                    }
                } else {
                    return this.setState({error: 'Password and Username Error'});
                }
            })
        return this.setState({error: ''});
    }

    handleUserChange(evt) {
        this.setState({
            username: evt.target.value,
        });
    };

    handlePassChange(evt) {
        this.setState({
            password: evt.target.value,
        });
    }

    render() {
        // NOTE: I use data-attributes for easier E2E testing
        // but you don't need to target those (any css-selector will work)

        return (
            <div className="wrapper fadeInDown">
                <div id="formContent">
                    {/*<h2 className="inactive underlineHover">Sign Up </h2>*/}

                    <div className="fadeIn first">
                        <img id="login" src={logo} alt="logo"/>

                    </div>

                    {/*<h2 className="active"> ورود </h2>*/}
                    <form onSubmit={this.handleSubmit}>

                        <input type="text" id="login" className="fadeIn second" name="login" placeholder="نام کاربری"
                               value={this.state.username} onChange={this.handleUserChange}/>

                        <input type="password" id="password" className="fadeIn third" name="login"
                               placeholder="رمز عبور" value={this.state.password} onChange={this.handlePassChange}/>

                        <input id="login2" type="submit" className="fadeIn fourth" value="ورود"/>
                    </form>

                    {/*<div id="formFooter">*/}
                    {/*    <a className="underlineHover" href="#">Forgot Password?</a>*/}
                    {/*</div>*/}

                </div>
            </div>
        )
    }
}

export default Login;