import './Counter.css'
export default function CounterButton({by, incrementCounterParent,decrementCounterParent}) {

   

    // function incrementCounter() {
    //     incrementCounterParent(by);
    //     console.log('Increment');
    // }

    // function decrementCounter() {
    //     decrementCounterParent(by);
    //     console.log('Decrement');
    // }

   

    return (
        <div className="Counter">
            
            <button className="CounterBtn"
                onClick={() => incrementCounterParent(by)}
            >
                + {by}
            </button>
            <button className="CounterBtn"
                onClick={() => decrementCounterParent(by)}
            >
                - {by}
            </button>

            

            
        </div>
    )
}