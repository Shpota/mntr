import axios from "axios";
import {useEffect, useState} from "react";
import useWebSocket from "react-use-websocket";
import "./ServicesTable.css"

function ServicesTable() {
    const [services, setServices] = useState([]);
    const {lastJsonMessage} = useWebSocket(`${process.env.REACT_APP_WS_API}/services`, {shouldReconnect: (closeEvent) => true});

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_REST_API}/services`)
            .then(resp => setServices(resp.data))
    }, []);

    useEffect(() => {
        if (services && lastJsonMessage) {
            const index = services.findIndex((it) => it.id === lastJsonMessage.id)
            if (index === -1) {
                setServices([...services, lastJsonMessage]);
            } else {
                const items = [...services];
                items[index] = lastJsonMessage;
                setServices(items);
            }
        }
    }, [lastJsonMessage])

    let formattedDate = (time) => new Date(time).toISOString().slice(0, 16).replace('T',' ')

    return (
        <>
            {services && services.length > 0 && (<table className="services-table">
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>URL</th>
                    <th>Creation Time</th>
                    <th>Status</th>
                </tr>
                {services.map((service, i) => <tr key={i}>
                    <td>{service.name}</td>
                    <td>{service.url}</td>
                    <td>{formattedDate(service.createdDate)}</td>
                    <td>{service.status}</td>
                </tr>)}
                </tbody>
            </table>)}
            {(!services || services.length === 0) && (<div>There are no services to monitor</div>)}
        </>
    );
}

export default ServicesTable;