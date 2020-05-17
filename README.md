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

UPCOMING EVENTS :

 1. Upcoming events view:
    1.1 User opens <Events> tab
    1.2 The user click on <Upcoming Events> tab
    1.3 The page contains a list of event's card. Cards count is equal to value of Upcoming Events tab counter;
2. Upcoming event card view:
    2.1 User opens <Events> tab
    2.2 The user click on <Upcoming Events> tab
    2.3 The page contains a list of event's card
    2.4 The card contains the following event info:
        • location and language
        • event name
        • event date
        • registry information
        • list of speakers
        Note : It is important to check order of displayed information
3. Upcoming events dates validation
    3.1 User opens <Events> tab
    3.2 The user click on <Upcoming Events> tab
    3.3 The page contains a list of event's card
    3.4 Event cards which are shown in the <This week> block have date, which is after or equal today date and is in current week range

4. View upcoming event detailed information:
    4.1 User opens <Events> tab
    4.2 The user click on <Upcoming Events> tab
    4.3 The page contains a list of event's card.
    4.4 The user clicks on any event card
    4.5 The event info page is opened
    4.6 The page contains the follwoing info
        • header with registry button,
        • event agenda,
        • event date, time, location

PAST EVENTS :

5. View past events in the Canada:
    5.1 User opens <Events> tab
    5.2 The user click on <Past Events> tab
    5.3 The user click on Location fiels in the filter block and chooses Canada in the dropdown list
    5.4 The page contains a list of event's card. Cards count is equal to value of Past Events tab counter;
    5.5 Date of the displayed events is before current date

TALKS LIBRARY :

6. Talks filter by several categories:
    6.1 User opens <Talks Library> tab
    6.2 User clicks on More Filters button
    6.3 User chooses : Category – Design, Location – Belarus, Language – English
    6.4 The page shows event cards which match with filter criteria

7. Search talks by keyword:
   7.1 User opens <Talks Library> tab
   7.2 User inout Azure in the search field
   7.3 The page shows event cards which contains the keyword


