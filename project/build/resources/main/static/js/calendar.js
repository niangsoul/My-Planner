const monthYear = document.getElementById('month-year');
const calendarBody = document.getElementById('calendar-body');
const prevBtn = document.getElementById('prev-month');
const nextBtn = document.getElementById('next-month');

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();

// 일정 임시 저장
let schedules = [];

// 캘린더 렌더링
function renderCalendar(month, year) {
    calendarBody.innerHTML = '';
    const firstDay = new Date(year, month, 1).getDay();
    const lastDate = new Date(year, month + 1, 0).getDate();
    monthYear.textContent = `${year}년 ${month + 1}월`;

    let date = 1;
    for (let i = 0; i < 6; i++) {
        const row = document.createElement('tr');
        for (let j = 0; j < 7; j++) {
            const cell = document.createElement('td');
            if (i === 0 && j < firstDay || date > lastDate) {
                cell.textContent = '';
            } else {
                cell.textContent = date;
                if(date === today.getDate() && month === today.getMonth() && year === today.getFullYear()){
                    cell.classList.add('today');
                }
                schedules.forEach(s => {
                    const d = new Date(s.date);
                    if(d.getFullYear() === year && d.getMonth() === month && d.getDate() === date){
                        const dot = document.createElement('div');
                        dot.textContent = s.title;
                        dot.style.fontSize = "0.8rem";
                        dot.style.color = "#4CAF50";
                        cell.appendChild(dot);
                    }
                });
                date++;
            }
            row.appendChild(cell);
        }
        calendarBody.appendChild(row);
    }
}

// 월 이동
prevBtn.addEventListener('click', () => {
    currentMonth--;
    if(currentMonth < 0){ currentMonth=11; currentYear--; }
    renderCalendar(currentMonth, currentYear);
});
nextBtn.addEventListener('click', () => {
    currentMonth++;
    if(currentMonth>11){ currentMonth=0; currentYear++; }
    renderCalendar(currentMonth, currentYear);
});

// 초기 렌더링
renderCalendar(currentMonth, currentYear);
