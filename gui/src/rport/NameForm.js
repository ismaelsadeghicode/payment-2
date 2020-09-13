import React from 'react';
import axios from "axios";
import AppHeader from "../common/AppHeader";
import './nameform.css';
import * as moment from 'jalali-moment';
import {
    DatePicker

} from "react-advance-jalaali-datepicker";

class NameForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value2: moment(),
            //  serviceurl :' http://'+window.location.host,
            serviceurl: 'http://192.168.50.150:8080',
            count: '',
            pn: '',
            num: '',
            value: '',
            amount: '',
            date: '',
            id: '',
            method: '',
            status: '',
            persons: [],
            datee: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handlepnChange = this.handlepnChange.bind(this);
        this.handlesizeChange = this.handlesizeChange.bind(this);
        this.handleamountChange = this.handleamountChange.bind(this);
        this.handletypeChange = this.handletypeChange.bind(this);
        this.handlestatusChange = this.handlestatusChange.bind(this);
        this.handledateChange = this.handledateChange.bind(this);
        this.handledateChange2 = this.handledateChange2.bind(this);
        this.handleidChange = this.handleidChange.bind(this);
        this.nextpage = this.nextpage.bind(this);
        this.prevpage = this.prevpage.bind(this);

    }

    componentDidMount() {
        if (window.sessionStorage.getItem('key1') != 'true') {
            return this.props.history.push('/');
        }
        this.setState({pn: 1});
        this.setState({num: 10});
        axios.get(this.state.serviceurl + `/api/v1/daily-report`
            , {
                params: {
                    page: 0,
                    size: 10
                }
            })
            .then(res => {
                const count = res.data.totalElements;
                const persons = res.data.content;
                console.log(count);
                this.setState({persons});

            })
    }

    nextpage() {
        this.setState({pn: this.state.pn + 1});
        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                requestDateTopup: this.state.date,
                page: this.state.pn,
                size: this.state.num,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })
    }

    prevpage() {
        this.setState({pn: this.state.pn - 1});
        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                requestDateTopup: this.state.date,
                page: this.state.pn - 2,
                size: this.state.num,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })


        console.log(this.state.pn)
    }

    handleamountChange(event3) {

        this.setState({amount: event3.target.value});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: event3.target.value,
                status: this.state.status,
                page: this.state.pn - 1,
                size: this.state.num,
                requestDateTopup: this.state.date,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })
    }

    handletypeChange(event4) {

        this.setState({method: event4.target.value});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: event4.target.value,
                amount: this.state.amount,
                status: this.state.status,
                page: this.state.pn - 1,
                size: this.state.num,
                requestDateTopup: this.state.date,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })
    }

    handlestatusChange(event5) {

        this.setState({status: event5.target.value});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: event5.target.value,
                page: this.state.pn - 1,
                size: this.state.num,
                requestDateTopup: this.state.date,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })
    }

    handledateChange(formatted, unix) {
        console.log(formatted);
        console.log(unix);
        this.setState({date: unix});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                page: this.state.pn - 1,
                size: this.state.num,
                requestDateTopup: unix,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })

        console.log(this.state.datee);
    }

    handledateChange2() {

        this.setState({date: ''});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                page: this.state.pn - 1,
                size: this.state.num,
                id: this.state.id

            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })

        console.log(this.state.datee);
    }

    handleidChange(event7) {

        this.setState({id: event7.target.value});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                page: this.state.pn - 1,
                size: this.state.num,
                id: event7.target.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                requestDateTopup: this.state.date,
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })
    }

    handleChange(event) {
        this.setState({value: event.target.value});

        this.setState({pn: 1});

        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: event.target.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                page: this.state.pn - 1,
                size: this.state.num,
                requestDateTopup: this.state.date,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })
    }

    handlepnChange(event1) {

        this.setState({pn: event1.target.value});
        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                requestDateTopup: this.state.date,
                page: event1.target.value - 1,
                size: this.state.num,
                id: this.state.id
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })


        console.log(this.state.pn)

    }

    handlesizeChange(event2) {
        this.setState({pn: 1});
        this.setState({num: event2.target.value});
        axios.get(this.state.serviceurl + `/api/v1/daily-report`, {
            params: {

                page: 0,
                size: event2.target.value,
                subscriberNo: this.state.value,
                method: this.state.method,
                amount: this.state.amount,
                status: this.state.status,
                id: this.state.id,
                requestDateTopup: this.state.date
            }
        })
            .then(res => {
                const persons = res.data.content;
                console.log(persons)
                this.setState({persons});
                // console.log(this.size.value)
            })


        console.log(this.state.num)

    }


    renderTableData() {

        return this.state.persons.map((charge, index) => {
            const {id, requestDateTimeTopup, subscriberNo, status, amount, method} = charge //destructuring
            return (

                <tr key={id}>

                    <td className="td">{subscriberNo}</td>
                    {/*<td>{charge.status ? 'شارژ شده' : 'شارژ نشده'}</td>*/}
                    <td className="td">{charge.status ? <div style={{color: 'Green'}}>{'شارژ شد'}</div> :
                        <div style={{color: 'red'}}>{'شارژ نشده'}</div>}</td>
                    <td className="td">{amount}</td>
                    {/*<td>{charge.method == 21 ? 'Irancel' : 'MCI'}</td>*/}
                    <td className="td" style={{"fontweight": "bold"}}>
                        {(() => {
                            if (charge.method == 3) {
                                return (
                                    <div style={{"color": "Blue"}}>{'MCI'}</div>
                                )
                            } else if (charge.method == 21 || charge.method == 22) {
                                return (
                                    <div style={{"color": "Orange"}}>{'IRANCEL'}</div>
                                )
                            } else if (charge.method == 41 || charge.method == 42) {
                                return (
                                    <div style={{"color": "Purple"}}>{'RIGHTEL'}</div>
                                )
                            }
                        })()}
                    </td>
                    <td className="td">
                        {moment(new Date(charge.requestDateTimeTopup).toISOString()).locale('fa').format('YYYY-MM-DD')}
                    </td>
                    {/*<td>{*/}
                    {/*    (new Date(charge.requestDateTimeTopup)).toString()*/}
                    {/*}</td>*/}
                    {/*<td>{requestDateTimeTopup}</td>*/}
                    <td className="td">{id}</td>
                    <td className="td">{index + 1}
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div>
                <head>
                    <meta charSet="utf-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
                    <link rel="stylesheet"
                          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"></link>
                    <link rel="stylesheet"
                          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                    <script
                        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
                </head>
                <div className="app-top-box">
                    <AppHeader/>
                </div>
                <div className="col-md-12" id="submenu3">

                    <a className="navig" id="submenu2" href="/nameform">گزارش ریز تراکنش ها</a>


                </div>
                <div className="fafa">
                    <div className="col-md-12" id="header"> گزارش ریز تراکنش ها</div>
                    <form onSubmit={this.handleSubmit}>
                        <div className="col-md-12">
                            <div className="col-md-3">
                                <label id="phone">

                                    شناسه
                                </label>

                                <input id="phone" className="form-control" type="text" value={this.state.id}
                                       onChange={this.handleidChange}/>
                            </div>
                            <div className="col-md-3">
                                <label id="phone">

                                    تاریخ
                                </label>
                                <div id="right">
                                    <DatePicker
                                        customClass="date form-control"
                                        value={this.state.date}
                                        placeholder="انتخاب تاریخ"
                                        format="jYYYY-jMM-jDD"
                                        onChange={this.handledateChange}
                                        id="datePicker"
                                        inputTextAlign="center"

                                    > </DatePicker>

                                </div>

                            </div>
                            <div className="col-md-3">
                                <label id="phone">
                                    مبلغ
                                </label>

                                <input id="phone" className="form-control" type="text" value={this.state.amount}
                                       onChange={this.handleamountChange}/>
                            </div>
                            <div className="col-md-3">
                                <label id="phone">

                                    شماره مشترک
                                </label>

                                <input id="phone" className="form-control" type="text" value={this.state.value}
                                       onChange={this.handleChange}/>
                            </div>
                            <div className="col-md-3"></div>
                            <div className="col-md-3"></div>
                            <div className="col-md-3">
                                <label id="phone">
                                    وضعیت
                                </label>

                                <select id="filter" className="form-control" onChange={this.handlestatusChange}>

                                    <option value='' selected></option>
                                    <option value='true'>شارژ شده</option>
                                    <option value='false'>شارژ نشده</option>


                                </select>
                            </div>
                            <div className="col-md-3">
                                <label id="phone">
                                    نوع شارژ
                                </label>

                                <select id="filter" className="form-control" onChange={this.handletypeChange}>
                                    <option value="" selected></option>
                                    <option value="MCI">همراه اول</option>
                                    <option value="IRANCEL">ایرانسل</option>
                                    <option value="RIGHTEL">رایتل</option>
                                </select>
                            </div>
                        </div>
                    </form>


                    <div>
                        <div>
                            <div id="size3">
                                <div id="number"></div>
                                <div id="size2"><label id="fa"> تعداد رکورد ها در صفحه </label>

                                    <select onChange={this.handlesizeChange}>

                                        <option value="10" selected>10</option>
                                        <option value="20">20</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>

                                    </select>

                                </div>

                                <div id="size">


                                </div>

                            </div>

                            <div id="table">
                                {/*<table id='students' align="right">*/}
                                <table
                                    cellSpacing="0"
                                    width="100%"
                                    className="table">
                                    <thead id="remain">
                                    <tr>
                                        <td className="td">شماره مشترک</td>
                                        <td className="td">وضعيت</td>
                                        <td className="td">مبلغ (ريال)</td>
                                        <td className="td">نوع شارژ</td>
                                        <td className="td">تاريخ</td>
                                        <td className="td">شناسه</td>
                                        <td className="td">ردیف</td>


                                    </tr>
                                    </thead>

                                    <tbody>
                                    {this.renderTableData()}
                                    </tbody>


                                </table>
                                <div className="container">
                                    <div className="col-md-4"></div>
                                    <div className="col-md-4" id="pn">
                                        <button className="btn" onClick={this.prevpage} o><i
                                            className="fa fa-chevron-left"></i></button>
                                        <input id="pn" value={this.state.pn} onChange={this.handlepnChange} type="text"
                                               placeholder="1"/>

                                        <button className="btn" onClick={this.nextpage}><i
                                            className="fa fa-chevron-right"></i></button>
                                    </div>
                                    <div className="col-md-4"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default NameForm;
