import { useState } from 'react';
import './Counter.css'
import CounterButton from './CounterButton';

export default function Counter(){
   const [count, setCount] = useState(0);

    function incrementCounterParent(by) {
        setCount(prevCount => prevCount +  parseInt(by));
        
    }

    function decrementCounterParent(by) {
        setCount(prevCount => prevCount -  parseInt(by));
        
    }
    function resetCounter() {
        setCount(0);
        console.log('Reset');
    }
    


    return (
        <div>
            <h1>Counter</h1>
            <p className='count'>{count}</p>
            <CounterButton by="1" incrementCounterParent={incrementCounterParent} decrementCounterParent={decrementCounterParent}/>
            <CounterButton by="5" incrementCounterParent={incrementCounterParent} decrementCounterParent={decrementCounterParent} />
            <CounterButton by="10" incrementCounterParent={incrementCounterParent}  decrementCounterParent={decrementCounterParent} />

            <button className="CounterBtn"
                onClick={resetCounter}
            >
                reset
            </button>
        </div>
    );

}


// Counter.propTypes ={
//     by:PropTypes.number.isRequired
// }