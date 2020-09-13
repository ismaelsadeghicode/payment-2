import React from 'react';
import Button from "reactstrap/es/Button";
import axios from "axios";
import AppHeader from "../common/AppHeader";
import './active.css'

// import './active.css';

class Active extends React.Component {

    state = {
       // serviceurl :' http://'+window.location.host,
        serviceurl :' http://192.168.50.150:8080',
        status: Boolean,
        persons: [],
        mci: [],
        mtn: [],
        rit: []
    }

    componentWillMount() {

        // if (window.sessionStorage.getItem('key1') != 'true'){
        //     return this.props.history.push('/');
        // }
        this.operatorStatusMci();
        this.operatorStatusMtn();
        this.operatorStatusRightel();

    }

    operatorStatusMci() {
        axios.get(this.state.serviceurl+`/api/v1/operator-status`, {
            params: {
                operatorName: "mci"
            }
        })
            .then(res => {
                const mci = res.data;
                this.setState({mci});
                console.log(this.state.mci.status)
            })

    }

    operatorStatusMtn() {
        axios.get(this.state.serviceurl+`/api/v1/operator-status`, {
            params: {
                operatorName: "mtn"
            }
        })
            .then(res => {
                const mtn = res.data;
                this.setState({mtn});
                console.log(this.state.mtn.status)
            })

    }

    operatorStatusRightel() {
        axios.get(this.state.serviceurl+`/api/v1/operator-status`, {
            params: {
                operatorName: "rightel"
            }
        })
            .then(res => {
                const rit = res.data;
                this.setState({rit});
                console.log(this.state.rit.status)
            })

    }

    signin = () => {
        // alert("وضعیت سرویس خرید شارژ همراه اول : فعال شد");
        axios.get(this.state.serviceurl+`/api/v1/direct-topup-mci-status`, {
            params: {
                status: true
            }
        })
            .then(res => {
                const persons = res.data;
                this.setState({persons});
                console.log(persons)
            })

        this.operatorStatusMci();
    }

    signindown = () => {
        // alert("وضعیت سرویس خرید شارژ همراه اول : غیرفعال شد");
        axios.get(this.state.serviceurl+`/api/v1/direct-topup-mci-status`, {
            params: {
                status: false
            }
        })
            .then(res => {
                const persons = res.data;
                this.setState({persons});
                console.log(persons)
            })

        this.operatorStatusMci();
    }


    signinupIrancel = () => {
        // alert("وضعیت سرویس خرید شارژ ایرانسل : فعال شد");
        axios.get(this.state.serviceurl+`/api/v1/direct-topup-mtn-status`, {
            params: {
                status: true
            }
        })
            .then(res => {
                const persons = res.data;
                this.setState({persons});
                console.log(persons)
            })

        this.operatorStatusMtn();
    }


    signindownIrancel = () => {
        // alert("وضعیت سرویس خرید شارژ ایرانسل : غیر فعال شد");
        axios.get(this.state.serviceurl+`/api/v1/direct-topup-mtn-status`, {
            params: {
                status: false
            }
        })
            .then(res => {
                const persons = res.data;
                this.setState({persons});
                console.log(persons)
            })

        this.operatorStatusMtn();
    }

    signinupRightel = () => {
        // alert("وضعیت سرویس خرید شارژ رایتل : فعال شد");
        axios.get(this.state.serviceurl+`/api/v1/direct-topup-rightel-status`, {
            params: {
                status: true
            }
        })
            .then(res => {
                const persons = res.data;
                this.setState({persons});
                console.log(persons)
            })

        this.operatorStatusRightel();
    }


    signindownRightel = () => {
        // alert("وضعیت سرویس خرید شارژ رایتل : غیر فعال شد");
        axios.get(this.state.serviceurl+`/api/v1/direct-topup-rightel-status`, {
            params: {
                status: false
            }
        })
            .then(res => {
                const persons = res.data;
                this.setState({persons});
                console.log(persons)
            })

        this.operatorStatusRightel();
    }


    render() {
        return (
            <div>
                <head>
                    <head>
                        <meta charSet="utf-8"></meta>
                        <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
                        <link rel="stylesheet"
                              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"></link>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                        <script
                            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
                    </head>
                </head>

                <div className="app-top-box">
                    <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout}/>
                </div>
                <div className="col-md-12" id="submenu">

                    <a className="navig"  id="submenu2" href="/remain">مانده اپراتورها</a>
                    <a className="navig"  id="submenu2" href="/active">وضعیت اپراتورها</a>
                    <a className="navig"  id="submenu2" href="/allreport">سرجمع تراکنش ها</a>

                </div>
                <div >
                    <div className="col-md-4" ></div>
                    <div className="col-md-4">
                        <div id='title' >فعال کردن و غیر فعال کردن اپراتورها</div>


                        <table align="center" cellSpacing="0" className="table">
                            <thead id="active">
                            <tr>
                                <td className="td">همراه اول</td>
                                <td className="td">ایرانسل</td>
                                <td className="td">رایتل</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td className="td"><Button id="mybtn1" onClick={this.signin}
                                >فعال</Button></td>
                                <td className="td"><Button id="mybtn1" onClick={this.signinupIrancel}
                                >فعال</Button></td>
                                <td className="td"><Button id="mybtn1" onClick={this.signinupRightel}
                                >فعال</Button></td>
                            </tr>

                            <tr>
                                <td className="td"><Button id="mybtn2" onClick={this.signindown} >غیر
                                    فعال</Button></td>
                                <td className="td"><Button id="mybtn2" onClick={this.signindownIrancel}
                                >غیر فعال</Button></td>
                                <td className="td"><Button id="mybtn2" onClick={this.signindownRightel}
                                >غیر فعال</Button></td>
                            </tr>

                            <tr>
                                <td className="td">{(() => {
                                    if (this.state.mci.status === true) {
                                        return (
                                            <div style={{"color": "Green", "font-weight": "bold"}}>{' فعال '}</div>
                                        )
                                    } else if (this.state.mci.status === false) {
                                        return (
                                            <div style={{"color": "red", "font-weight": "bold"}}>{' غیرفعال '}</div>
                                        )
                                    }
                                })()}
                                </td>
                                <td className="td">
                                    {(() => {
                                        if (this.state.mtn.status === true) {
                                            return (
                                                <div style={{"color": "Green", "font-weight": "bold"}}>{' فعال '}</div>
                                            )
                                        } else if (this.state.mtn.status === false) {
                                            return (
                                                <div style={{"color": "red", "font-weight": "bold"}}>{' غیرفعال '}</div>
                                            )
                                        }
                                    })()}
                                </td>
                                <td className="td">
                                    {(() => {
                                        if (this.state.rit.status === true) {
                                            return (
                                                <div style={{"color": "Green", "font-weight": "bold"}}>{' فعال '}</div>
                                            )
                                        } else if (this.state.rit.status === false) {
                                            return (
                                                <div style={{"color": "red", "font-weight": "bold"}}>{' غیرفعال '}</div>
                                            )
                                        }
                                    })()}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    </div>
            </div>
        )
    }


}

export default Active;
