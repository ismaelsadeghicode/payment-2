import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import './AppHeader.css';
import logo from '../img/logo-new.png'




class AppHeader extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            addClass: false
        }
        this.exit = this.exit.bind(this);
    }
    exit(){
        localStorage.removeItem('key');
        window.sessionStorage.removeItem('key1');
    }
    componentWillMount() {
     this.setState({username : localStorage.getItem('key')});
    }
    toggle() {
        this.setState({addClass: !this.state.addClass});
    }
    render() {
        let boxClass = ["box"];
        if(this.state.addClass) {
            boxClass.push('green');
        }
        return (
            <div>
                <head>
                    <meta charSet="utf-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
                    <link rel="stylesheet"
                          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"></link>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                    <script
                        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
                </head>

            <header className="app-header" >

                <div id="header"  >

                    {/*<a href="#default" className="logo">CompanyLogo</a>*/}
                    <div  id="img" className="col-md-1.5" >
                        <img id="img1" src={logo} alt="logo"/>
                    </div>
                    <div  id="menu" className="col-md-6.5" >




                        <a className="navig" href="/nameform">گزارشات</a>
                        <a className="navig"  href="/operator">اپراتور</a>




                    </div>
                    <div id="user" className="col-md-2">
                        <a href="/" id="exit" onClick={this.exit} >خروج</a>
                        <button id="username"   >
                            {this.state.username}
                        </button>


                    </div>
                </div>
                <div  id="line">
                </div>




            </header>
            </div>
        )

    }
}

export default AppHeader;