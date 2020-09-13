import React from 'react';

import axios from 'axios';
import './report.css';
import './table.css';

import AppHeader from "../common/AppHeader";


class Report extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: '',
pn:'',
            num: '',
            persons: []
        };
        this.handlepnChange = this.handlepnChange.bind(this);
        this.handlenumChange = this.handlenumChange.bind(this);



    }
    handlenumChange(event) {
        this.setState({num: event.target.value});

        axios.get(`http://192.168.50.150:8080/api/v1/daily-report`, {
            params: {
                page: this.state.pn,
                size:  event.target.value,
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
            })
        event.preventDefault();
    }

    handlepnChange(event) {
        this.setState({pn: event.target.value});

        axios.get(`http://192.168.50.150:8080/api/v1/daily-report`, {
            params: {
                page: event.target.value,
                size: this.state.num,
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
            })
        event.preventDefault();
    }

    componentDidMount() {
        if (sessionStorage.getItem('key1') != 'true'){
            return this.props.history.push('/operator');
        }
        this.setState({pn: 1});
        axios.get(`http://192.168.50.150:8080/api/v1/daily-report`
            , {
                params: {
                    page: 1,
                    size: 10
                }
            })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                console.log(persons)
            })
    }

    renderTableData() {
        return this.state.persons.map((student, index) => {
            const {id, requestDateTimeTopup, subscriberNo, status, amount, method} = student //destructuring
            return (

                <tr key={id}>
                    <td>{subscriberNo}</td>
                    {/*<td>{student.status ? 'شارژ شده' : 'شارژ نشده'}</td>*/}
                    <td>{student.status ? <div style={{color: 'Green'}}>{'شارژ شد'}</div> :
                        <div style={{color: 'red'}}>{'شارژ نشده'}</div>}</td>
                    <td>{amount}</td>
                    {/*<td>{student.method == 21 ? 'Irancel' : 'MCI'}</td>*/}
                    <td style={{"font-weight": "bold"}}>
                        {(() => {
                            if (student.method == 3) {
                                return (
                                    <div style={{"color": "Blue"}}>{'MCI'}</div>
                                )
                            } else if (student.method == 21 || student.method == 22) {
                                return (
                                    <div style={{"color": "Orange"}}>{'IRANCEL'}</div>
                                )
                            } else if (student.method == 41 || student.method == 42) {
                                return (
                                    <div style={{"color": "Purple"}}>{'RIGHTEL'}</div>
                                )
                            }
                        })()}
                    </td>
                    <td>
                        {(new Date(student.requestDateTimeTopup)).getFullYear() + '-' +
                        (new Date(student.requestDateTimeTopup)).getMonth() + '-' +
                        (new Date(student.requestDateTimeTopup)).getDay()}
                    </td>
                    {/*<td>{*/}
                    {/*    (new Date(student.requestDateTimeTopup)).toString()*/}
                    {/*}</td>*/}
                    {/*<td>{requestDateTimeTopup}</td>*/}
                    <td>{id}</td>
                </tr>
            )
        })
    }

    render() {
        return (

            <div className="container">
                <div className="app-top-box">
                    <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout}/>
                </div>

                <div id='title' className="col-md-12">گزارش کل تراکنش ها</div>
                {/*<table id='students' align="right">*/}
                <div id="size4">

                    <div id="size2"><label id="fa"> تعداد رکورد ها در صفحه :</label>
                        <select value={this.state.num} onChange={this.handlenumChange}>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select>

                    </div>

                    <div id="size">


                    </div>

                </div>
                <div className="col-md-12" id="table">
                    <table id="report" className="table-dark" cellSpacing="0"
                           width="100%">
                        <thead id="report">
                        <tr id="report">
                            <td>شماره مشترک</td>
                            <td>وضعيت</td>
                            <td>مبلغ (ريال)</td>
                            <td>نوع شارژ</td>
                            <td>تاريخ</td>
                            <td>شناسه</td>
                        </tr>
                        </thead>

                        <tbody>
                        {this.renderTableData()}
                        </tbody>


                    </table>
                    <div id="pn">
                        <input id="pn" value={this.state.pn} onChange={this.handlepnChange} type="text" placeholder="1"/>
                        شماره صفحه

                    </div>
                </div>
            </div>

        )
    }
}

export default Report;
