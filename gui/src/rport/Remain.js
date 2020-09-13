import React from 'react';
import Button from "reactstrap/es/Button";
import axios from "axios";
import AppHeader from "../common/AppHeader";
import './remain.css'

class Remain extends React.Component {

    state = {
       // serviceurl :' http://'+window.location.host,
        serviceurl :' http://192.168.50.150:8080',
        remainmci: [],
        remainmtn: [],
        remainrit: [],

    }

    componentWillMount() {
        if (sessionStorage.getItem('key1') != 'true'){
            return this.props.history.push('/');
        }
        this.runRemainMti();
        this.runRemainIrancel();
        this.runRemainrightel();
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

    render() {
        return (
            <div>
                <head>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react-dom.min.js"></script>
                    <div id="root"></div>
                </head>
                <div className="app-top-box">
                    <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout} />
                </div>

                <div className="col-md-12" id="submenu">

                    <a className="navig"  id="submenu2" href="/remain">مانده اپراتورها</a>
                    <a className="navig"  id="submenu2" href="/active">وضعیت اپراتورها</a>
                    <a className="navig"  id="submenu2" href="/allreport">سرجمع تراکنش ها</a>

                </div>
               <div >
                   <div className="col-md-4" ></div>
                   <div className="col-md-4" >

                   <div id='title'   >مانده اپراتورها</div>

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
               </div>
            </div>
        )
    }

}

export default Remain;
