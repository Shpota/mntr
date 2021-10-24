import './AddServiceForm.css';
import {useState} from "react";
import axios from "axios";

function AddServiceForm() {
    let [name, setName] = useState("");
    let [url, setUrl] = useState("");

    let onFormSubmit = () => {
        axios.post(`${process.env.REACT_APP_REST_API}/services`, {
            name: name,
            url: url,
        }).then(resp => {
            setUrl("")
            setName("")
        })
    }

    return <div className="add-service">
        <input type="text" placeholder="Enter service name" value={name}
               onChange={(event) => setName(event.target.value)}/>
        <input type="text" placeholder="Enter URL" value={url} onChange={(event) => setUrl(event.target.value)}/>
        <button className="favorite styled" type="button" disabled={!name || !url} onClick={onFormSubmit}>
            Add service
        </button>
    </div>
}

export default AddServiceForm