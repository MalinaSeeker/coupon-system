export async function fetchAllCoupons({pageNumber, pageSize}) {
    try {
        const response = await fetch(`http://localhost:8080/api/coupon/all?pageNumber=${pageNumber}&size=${pageSize}&sortProperty=title`)
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error:', error);
    }
}

export async function fetchAllCategories() {
    try {
        const response = await fetch(`http://localhost:8080/api/coupon/categories`)
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error:', error);
    }
}

export async function fetchPurchasedCoupons({customerToken}) {
    try {
        const response = await fetch('http://localhost:8080/api/customer/purchased-coupons', {
            headers: {
                'Authorization': customerToken,
            }
        })
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error: ', error);
    }
}


export async function fetchNotPurchasedCoupons({customerToken}) {
    try {
        const response = await fetch(`http://localhost:8080/api/customer/not-purchased-coupons`, {
            headers: {
                'Authorization': customerToken
            }
        })
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error: ', error)
    }
}

export async function fetchCoupon({uuid}) {
    try {
        const response = await fetch(`http://localhost:8080/api/coupon/single?coupon=${uuid}`)
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error:', error);
    }
}

export async function purchaseCoupon({customerToken, couponUuid}) {
    try {
        const response = await fetch(`http://localhost:8080/api/customer/purchase?coupon=${couponUuid}`, {
            method: 'POST',
            headers: {
                'Authorization': customerToken
            }
        })
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error:', error);
    }
}

export async function fetchCouponsByCategory({customerToken, category}) {
    try {
        const response = await fetch(`http://localhost:8080/api/customer/by-category?category=${category}`, {
            headers: {
                'Authorization': customerToken
            }
        })
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error: ', error)
    }
}


export async function fetchExpiredInWeekCoupons({customerToken}) {
    try {
        const response = await fetch(`http://localhost:8080/api/customer/in-a-week`, {
            headers: {
                'Authorization': customerToken
            }
        })
        return await checkAndReturnResponse({response})
    } catch (error) {
        console.error('Error: ', error)
    }
}


async function checkAndReturnResponse({response}) {
    if (!response.ok) {
        const problemDetail = await response.json()
        if (problemDetail.title === "Unauthorized") {
            localStorage.clear()
            window.location.reload()
            throw new Error(problemDetail.detail);
        } else {
            throw new Error(problemDetail.detail || response.statusText);
        }
    } else {
        return response.json()
    }
}
