import React, {Component} from "react";
import {Card, Form, Button, Col} from 'react-bootstrap';
import axios from "axios";
import {DateTimePickerComponent} from '@syncfusion/ej2-react-calendars';
import SuccessAlert from "./SuccessAlert";
import ErrorAlert from "./ErrorAlert";

export default class AddReservation extends Component{

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.reservationChange = this.reservationChange.bind(this);
        this.submitReservation = this.submitReservation.bind(this);
    }

    initialState = {
        name:'', surname:'', phone:'', car:'', date:''
    }

    resetReservation = () =>{
        this.setState(() => this.initialState);
    }

    submitReservation = event => {
        event.preventDefault();

        const customer = {
            name: this.state.name,
            surname: this.state.surname,
            phone: this.state.phone,
            car: this.state.car,
            date: this.state.date
            };

            axios.post("http://localhost:8080/rest/reservations", customer)
                .then(response => {
                    if(response.data != null) {
                        this.setState(this.initialState);
                        alert("Objednávka byla úspěšně přidána!");
                        }
                    })
                .catch(function (error) {

                    alert("Zvolené datum/čas je plně obsazeno! Zvolte prosím jiné datum/čas")

                    if (error.response) {
                        console.log(error.response.data);
                        console.log(error.response.status);
                        console.log(error.response.headers);
                    } else if (error.request) {
                        console.log(error.request);
                    } else {
                        console.log('Error', error.message);
                    }
                });
        }

    reservationChange = event => {
        this.setState({
        [event.target.name]:event.target.value
        });
    }


    render() {
        const {name, surname, phone, car, date} = this.state;

        return (
            <div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>Add Reservation</Card.Header>
                    <Form onReset={this.resetReservation} onSubmit={this.submitReservation} id="reservationFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridName">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="name"
                                                  value={name}
                                                  onChange={this.reservationChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Enter name" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="formGridSurname">
                                    <Form.Label>Surname</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="surname"
                                                  value={surname}
                                                  onChange={this.reservationChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Enter surname" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridCar">
                                    <Form.Label>Car</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="car"
                                                  value={car}
                                                  onChange={this.reservationChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Enter Car" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="formGridPhone">
                                    <Form.Label>Phone Number</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="phone"
                                                  value={phone}
                                                  onChange={this.reservationChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Enter Phone Number" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <DateTimePickerComponent placeholder="Choose date"
                                                         min={new Date()}
                                                         format="yyyy-MM-dd HH:mm"
                                                         step={60}
                                                         type="date" name="date"
                                                         value={date}
                                                         onChange={this.reservationChange}
                                >
                                </DateTimePickerComponent>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}>
                            <Button size="sm" variant="success" type="submit">

                                Create Reservation
                            </Button>{' '}
                            <Button size="sm" variant="info" type="reset">

                                Reset
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}
