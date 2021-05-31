import React, {Component} from "react";
import {Card, Table, Button, ButtonGroup} from 'react-bootstrap';
import axios from "axios";

export default class ReservationList extends Component{

    constructor(props) {
        super(props);
        this.state = {
           reservations : []
        };
    }

    componentDidMount() {
        this.getAllReservations();
    }

    getAllReservations(){
        axios.get("http://localhost:8080/rest/reservations")
            .then(response => response.data)
            .then((data) => {
                this.setState({reservations: data})
            });
    }

    render() {
        return (
             <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Reservation List</Card.Header>
                 <Card.Body>
                     <Table bordered hover striped variant="dark">
                         <thead>
                         <tr>
                             <th>Name</th>
                             <th>Surname</th>
                             <th>Phone Number</th>
                             <th>Car</th>
                             <th>Date</th>
                         </tr>
                         </thead>
                         <tbody>
                         {
                             this.state.reservations.length === 0 ?
                            <tr align="center">
                                <td colSpan="6">Reservations Available</td>
                            </tr> :
                                 this.state.reservations.map((reservation) => (
                            <tr key={reservation.id}>
                                <td>{reservation.name}</td>
                                <td>{reservation.surname}</td>
                                <td>{reservation.phone}</td>
                                <td>{reservation.car}</td>
                                <td>{reservation.date}</td>
                             <td>
                                <ButtonGroup>
                                    <Button size="sm" variant="outline-primary"></Button>
                                    <Button size="sm" variant="outline-danger"></Button>
                                </ButtonGroup>
                             </td>
                            </tr>
                                 ))
                         }
                         </tbody>
                     </Table>
                 </Card.Body>
             </Card>
        );
    }
}