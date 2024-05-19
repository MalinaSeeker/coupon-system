export async function submitLogin({email, password}) {
    try {
        const response = await fetch(`http://localhost:8080/api/login/client?type=Customer`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({email, password})
        })
        if (!response.ok) {
            throw new Error(response.statusText);
            /*check how the error is thrown. you want it displayed to the customer*/
        }
        return await response.text()

    } catch (error) {
        console.error('Error:', error.message)
    }
}

export async function submitSignUp({firstName, lastName, email, password}) {
    try {
        const response = await fetch(`http://localhost:8080/api/signup/customer?password=${password}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({email, firstName, lastName})
        })
        if (!response.ok) {
            throw new Error(response.statusText);
            /*check how the error is thrown. you want it displayed to the customer*/
        }
    } catch (error) {
        console.error('Error:', error.message)
    }
}