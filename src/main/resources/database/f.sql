select *
from subscription
inner join league l on l.id = subscription.league_id
inner join translation t on l.id = t.league_id
inner join commentator c on t.commentator_id = c.id
where subscription.user_id = 1


