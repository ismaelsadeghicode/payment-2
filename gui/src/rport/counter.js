import React from 'react';

import axios from 'axios';
import './counter.css';
import './table.css';

import AppHeader from "../common/AppHeader";
import Button from "reactstrap/es/Button";



class Counter extends React.Component {




    state = {
       // serviceurl :' http://'+window.location.host,
        serviceurl :' http://192.168.50.150:8080',
        remainmci: [],
        remainmtn: [],
        remainrit: [],
        allreport: [],
        status: Boolean,
        persons: [],
        mci: [],
        mtn: [],
        rit: []
    }

    componentWillMount() {
        console.log(sessionStorage.getItem('key1'));
        if (window.sessionStorage.getItem('key1') != 'true'){
            return this.props.history.push('/');
        }
        this.runRemainMti();
        this.runRemainIrancel();
        this.runRemainrightel();
        this.operatorStatusMci();
        this.operatorStatusMtn();
        this.operatorStatusRightel();
        this.getreport();
        this.interval = setInterval(() => {
            this.getreport();
        }, 5000);
        this.interval = setInterval(() => {
            this.runRemainIrancel();
        }, 5000);
        this.interval = setInterval(() => {
            this.runRemainMti();
        }, 5000);
        this.interval = setInterval(() => {
            this.runRemainrightel();
        }, 5000);
    }

    getreport() {
        axios.get(this.state.serviceurl+`/api/v1/daily-report/total`)
            .then(res => {
                const allreport = res.data;
                this.setState({allreport});

            })

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


    runRemainMti = () => {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Basic cGFyc2lhbl90b3B1cDokPFBAUlNJQU46Pjw6dG9wdXA+IQ==',
        };

        // alert("");
        axios.get(`http://192.168.50.150:8080/api/v1/remain-balance-mci`, {headers})
            .then(res => {
                const remainmci = res.data;
                this.setState({remainmci});
                console.log(this.state.remainmci.response.remainCharge)
            })
    }

    runRemainIrancel = () => {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Basic cGFyc2lhbl90b3B1cDokPFBAUlNJQU46Pjw6dG9wdXA+IQ==',
        };

        // alert("");
        axios.get(`http://192.168.50.150:8080/api/v1/remain-balance-mtn-rightel`, {headers})
            .then(res => {
                const remainmtn = res.data;
                this.setState({remainmtn: remainmtn});
                console.log(this.state.remainmtn.response.remainCharge)
            })
    }

    runRemainrightel = () => {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Basic cGFyc2lhbl90b3B1cDokPFBAUlNJQU46Pjw6dG9wdXA+IQ==',
        };

        // alert("");
        axios.get(`http://192.168.50.150:8080/api/v1/remain-balance-mtn-rightel`, {headers})
            .then(res => {
                const remainrit = res.data;
                this.setState({remainrit});
                console.log(this.state.remainrit.response.remainCharge)
            })
    }
    componentWillUnmount() {
        clearInterval(this.interval);
    }
    render() {

        return (
            <div>
                <head>
                    <meta charSet="utf-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
                            <link rel="stylesheet"
                                  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"></link>
                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                <script
                                    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
                </head>
                <div className="app-top-box">
                    <AppHeader />
                </div>


<div className="col-md-12" id="submenu">

    <a className="navig"  id="submenu2" href="/remain">مانده اپراتورها</a>
    <a className="navig"  id="submenu2" href="/active">وضعیت اپراتورها</a>
    <a className="navig"  id="submenu2" href="/allreport">سرجمع تراکنش ها</a>

</div>
                <div className="fafa" >
                    <div className="col-md-12" id="header"> اپراتور</div>
                    <div className="col-md-4">

                    <div id='title'   ><a id="gotolink" href="/remain"> مانده اپراتورها</a></div>

                    <table align="center" cellSpacing="0" className="table">
                        <thead id="remain">
                        <tr>

                            <td className="td">مانده (ریال)</td>
                            <td className="td">نام اپراتور</td>
                            <td className="td" >ردیف</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>

                            <td className="td"> {(() => {
                                if (this.state.remainmci.successful && this.state.remainmci.response.remainCharge > 0) {
                                    return (
                                        <div style={{"color": "Blue"}}>{this.state.remainmci.response.remainCharge}</div>
                                    )
                                } else if (this.state.remainmci.successful && this.state.remainmci.response.remainCharge == 0) {
                                    return (
                                        <div style={{"color": "Red"}}>{'اعتبار ناکافی می باشد'}</div>
                                    )
                                }
                            })()}</td>
                            <td className="td">همراه اول</td>
                            <td className="td">1</td>
                        </tr>
                        <tr>

                            <td className="td">
                                {(() => {
                                    if (this.state.remainmtn.successful && this.state.remainmtn.response.remainCharge > 0) {
                                        return (
                                            <div style={{"color": "Blue"}}>{this.state.remainmtn.response.remainCharge}</div>
                                        )
                                    } else if (this.state.remainmtn.successful && this.state.remainmtn.response.remainCharge == 0) {
                                        return (
                                            <div style={{"color": "Red"}}>{'اعتبار ناکافی می باشد'}</div>
                                        )
                                    }
                                })()}
                            </td>
                            <td className="td">ایرانسل</td>
                            <td className="td">2</td>
                        </tr>
                        <tr>

                            <td className="td">{(() => {
                                if (this.state.remainrit.successful && this.state.remainrit.response.remainCharge > 0) {
                                    return (
                                        <div style={{"color": "Blue"}}>{this.state.remainrit.response.remainCharge}</div>
                                    )
                                } else if (this.state.remainrit.successful && this.state.remainrit.response.remainCharge >= 0) {
                                    return (
                                        <div style={{"color": "Red"}}>{'اعتبار ناکافی می باشد'}</div>
                                    )
                                }
                            })()}</td>
                            <td className="td">رایتل</td>
                            <td className="td">3</td>
                        </tr>
                        </tbody>
                    </table>

                    </div>

                    <div className="col-md-4">
                        <div id='title' ><a id="gotolink" href="/active">فعال کردن و غیر فعال کردن اپراتورها</a></div>


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
                                        <div style={{"color": "Green", "fontweight": "bold"}}>{' فعال '}</div>
                                    )
                                } else if (this.state.mci.status === false) {
                                    return (
                                        <div style={{"color": "red", "fontweight": "bold"}}>{' غیرفعال '}</div>
                                    )
                                }
                            })()}
                            </td>
                            <td className="td">
                                {(() => {
                                    if (this.state.mtn.status === true) {
                                        return (
                                            <div style={{"color": "Green", "fontweight": "bold"}}>{' فعال '}</div>
                                        )
                                    } else if (this.state.mtn.status === false) {
                                        return (
                                            <div style={{"color": "red", "fontweight": "bold"}}>{' غیرفعال '}</div>
                                        )
                                    }
                                })()}
                            </td>
                            <td className="td">
                                {(() => {
                                    if (this.state.rit.status === true) {
                                        return (
                                            <div style={{"color": "Green", "fontweight": "bold"}}>{' فعال '}</div>
                                        )
                                    } else if (this.state.rit.status === false) {
                                        return (
                                            <div style={{"color": "red", "fontweight": "bold"}}>{' غیرفعال '}</div>
                                        )
                                    }
                                })()}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                    <div className="col-md-4">
                        <div id='title' ><a id="gotolink" href="/allreport">سرجمع روزانه تراکنش ها</a></div>

                    <table align="center" cellSpacing="0" className="table">
                        <thead id="remain">
                        <tr>

                            <td className="td">جمع تراکنش ها در روز</td>
                            <td className="td" >نام اپراتور</td>
                            <td className="td">ردیف</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>

                            <td className="td">{this.state.allreport.mci}</td>
                            <td className="td">همراه اول</td>
                            <td className="td" >1</td>
                        </tr>
                        <tr>

                            <td className="td">
                                {this.state.allreport.mtn}
                            </td>
                            <td className="td">ایرانسل</td>
                            <td className="td">2</td>
                        </tr>
                        <tr>

                            <td className="td">{this.state.allreport.rightel}</td>
                            <td className="td">رایتل</td>
                            <td className="td">3</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>


        )}
}

export default Counter;
