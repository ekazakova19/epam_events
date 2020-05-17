# EPAM events portal test framework

The project is a test framework for EPAM event portal https://events.epam.com/.
It is a course work of OTUS JAVA QA Automation course.

# Implemented framework requirements
  1. Tech stack - Java + Maven + Junit5
  2. Logging with Log4j2
  3. Web driver factory for cross-browser test
  4. Remote test execution using Selenoid
  5. Parallel test execution
  6. Allure as a report tool
  7. Page Object pattern for page description
  8. Jenkins pipeline as CI tool

# Test coverage
The following test cases has been automated :

**Upcoming events view:**
 1. User opens <Events> tab
 2. The user click on <Upcoming Events> tab
 3. The page contains a list of event's card. Cards count is equal to value of Upcoming Events tab counter;

**Upcoming event card view:**
 1. User opens <Events> tab
 2. The user click on <Upcoming Events> tab
 3. The page contains a list of event's card
 4. The card contains the following event info:
    * location and language
    * event name
    * event date
    * registry information
    * list of speakers
    _Note : It is important to check order of displayed information_

**Upcoming events dates validation**
 1. User opens <Events> tab
 2. The user click on <Upcoming Events> tab
 3. The page contains a list of event's card
 4. Event cards which are shown in the <This week> block have date, which is after or equal today date and is in current week range

**View upcoming event detailed information:**
 1. User opens <Events> tab
 2. The user click on <Upcoming Events> tab
 3. The page contains a list of event's card.
 4. The user clicks on any event card
 5. The event info page is opened
 6. The page contains the follwoing info
        • header with registry button,
        • event agenda,
        • event date, time, locatio

**View past events in the Canada:**
 1. User opens <Events> tab
 2. The user click on <Past Events> tab
 3. The user click on Location fiels in the filter block and chooses Canada in the dropdown list
 4. The page contains a list of event's card. Cards count is equal to value of Past Events tab counter;
 5. Date of the displayed events is before current date

**Talks filter by several categories:**
 1. User opens <Talks Library> tab
 2. User clicks on More Filters button
 3. User chooses : Category – Design, Location – Belarus, Language – English
 4. The page shows event cards which match with filter criteria

**Search talks by keyword:**
 1. User opens <Talks Library> tab
 2. User inout Azure in the search field
 3. The page shows event cards which contains the keyword


