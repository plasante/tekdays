<html>
	<head>
		<title>TekDays - Dashboard</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div class="nav">
			<span class="menuButton">
				<a href="${resource(dir:'')}">Home</a>
			</span>
			<span class="menuButton">
				<g:link class="create" controller="task" action="create">
					Create Task
				</g:link>
			</span>
			<span class="menuButton">
				<g:link class="create" controller="sponsorship" action="create">
					Add Sponsor
				</g:link>
			</span>
			<span class="menuButton">
				<g:link class="list" controller="sponsor" action="list">
					All Sponsors
				</g:link>
			</span>
		</div>
		
		<div id="event" style='margin: 10px 10px 10px 10px'>
			<g:render template="event" model="${['event':event]}"/>
		</div>
		<div id="tasks" style='margin: 10px 10px 10px 10px'>
			<g:render template="tasks" model="${['tasks':tasks]}"/>
		</div>
		<div id="volunteers" style='margin: 10px 10px 10px 10px'>
			<g:render template="event" model="${['volunteers':volunteers]}"/>
		</div>
		<div id="messages" style='margin: 10px 10px 10px 10px'>
			<g:render template="event" model="${['messages':messages]}"/>
		</div>
		<div id="sponsors" style='margin: 10px 10px 10px 10px'>
			<g:render template="event" model="${['sponsors':sponsors]}"/>
		</div>
	</body>
</html>