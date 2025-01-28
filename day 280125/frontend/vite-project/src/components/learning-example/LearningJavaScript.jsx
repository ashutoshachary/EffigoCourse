const person ={
    name: 'Ashu',
    address:{
        city: 'New York',
        state: 'NY',
        country: 'USA'
    },
    age: 28,
    hobbies: ['Reading', 'Gaming', 'Coding'],
    printPerson: () =>{
        console.log(person);
        person.hobbies.forEach(hobby => console.log(hobby));
       
    }
}




export default function LearningJavaScript(){
    return (
        <>

        <div>{person.name}</div>
        <div>{person.address.city}</div>
        <div>{person.address.state}</div>
        <div>{person.address.country}</div>

        <div>{person.age}</div>
        <div>{person.hobbies}</div>
        <div>{person.printPerson()}</div>
        <button onClick={person.printPerson}>Print Person Info</button>

        </>
    )
}