import React from 'react';

import axios from 'axios';
import './allreport.css';
import './table.css';

import AppHeader from "../common/AppHeader";
import Button from "reactstrap/es/Button";



class allreport extends React.Component {
    state = {
       // serviceurl :' http://'+window.location.host,
        serviceurl :' http://192.168.50.150:8080',
      allreport : []

    }
    componentWillMount()
    {    if (window.sessionStorage.getItem('key1') != 'true'){
        return this.props.history.push('/');
    }
        this.getreport();
        this.interval = setInterval(() => {
            this.getreport();
        }, 5000);

    }

    getreport() {
        axios.get(this.state.serviceurl+`/api/v1/daily-report/total`)
            .then(res => {
                const allreport = res.data;
                this.setState({allreport});

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
                <div >
                    <div className="col-md-4" ></div>
                    <div className="col-md-4" >
                        <div id='title' >سرجمع روزانه تراکنش ها</div>

                        <table align="center"  cellSpacing="0" className="table">
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

export default allreport;
