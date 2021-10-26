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

    let isValidUrl = (url) => {
        let parsed;
        try {
            parsed = new URL(url);
        } catch (_) {
            return false;
        }
        return parsed.protocol === "http:" || parsed.protocol === "https:";
    }

    return <div className="add-service">
        <input type="text" placeholder="Enter service name" value={name}
               onChange={(event) => setName(event.target.value)}/>
        <input type="text" placeholder="Enter URL" value={url} onChange={(event) => setUrl(event.target.value)}/>
        <button className="favorite styled" type="button" disabled={!name || !url || !isValidUrl(url)} onClick={onFormSubmit}>
            Add service
        </button>
        {url && !isValidUrl(url) && (<p className="validation-error">Please specify valid URL including protocol, eg https://facebook.com</p>)}

    </div>
}

export default AddServiceForm