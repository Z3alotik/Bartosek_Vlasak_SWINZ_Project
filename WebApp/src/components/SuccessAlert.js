import React from 'react';


class SuccessAlert extends React.Component{
    render(){
        return(
            <div className="alert alert-success" role="alert">
                <strong>Objednávka byla úspěšně přidána!</strong>
            </div>
        );
    }

}


export default SuccessAlert