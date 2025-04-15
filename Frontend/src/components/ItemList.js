import React, { useState , useEffect } from 'react';
import axios from 'axios';


const ItemList = () => {
    const [items, setItems] = useState([]);
    const [page, setPages] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const pageSize = 10;

    // Fetch the items when the component mounts
    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/v1/item/paged' , {
                    params: {
                        page: page,
                        size: pageSize
                }});
                setItems(response.data.content);
                setTotalPages(response.data.totalPages);
            } catch (error){
                console.error("Error fetching items:", error);
            }
            };

        fetchItems();
    }, [page]);

    const handleClickNext = () => {
        if (page < totalPages -1)
        setPages(page + 1);
    };

    const handleClickPrev = () => {
        if(page>0)
        setPages(page - 1);
    };

    const thStyle = {
        padding: '12px',
        textAlign: 'left',
        borderBottom: '2px solid #ccc'
    };
    
    const tdStyle = {
        padding: '10px',
        textAlign: 'left'
    };

    return (
        <div style={{display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            gap: '12px',
            marginTop: '20px',
            padding: '10px',
            backgroundColor: '#f4f4f4',
            borderRadius: '10px',
            boxShadow: '0 12px 12px rgba(0, 0, 0, 0.1)'
            }}>
            <table style={{width: '95%',
                    borderCollapse: 'collapse',
                    marginTop: '20px'
                    }}>
                <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}> 
                        <th style={thStyle}>ID</th>
                        <th style={thStyle}>Name</th>
                        <th style={thStyle}>Option</th>
                        <th style={thStyle}>Time</th>
                        <th style={thStyle}>Meshfilename</th>
                        <th style={thStyle}>Desc</th>
                    </tr>
                </thead>
                <tbody>
                {items.map((item) => (
                    <tr key={item.id}>
                        <td style={tdStyle}>{item.idName}</td>
                        <td style={tdStyle}>{item.name}</td>
                        <td style={tdStyle}>{item.option}</td>
                        <td style={tdStyle}>{item.time}</td>
                        <td style={tdStyle}>{item.meshfilename}</td>
                        <td style={tdStyle}>{item.desc}</td>
                    </tr>
                ))}
               <button className='btn' onClick={handleClickPrev} disabled={page === 0}>Prev</button>
               <button className="btn" onClick={handleClickNext} disabled={page === totalPages -1}>Next</button>
               </tbody>
            </table>
        </div>
    );
}

export default ItemList;