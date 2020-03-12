# Clean code standards

* Break down a big CR to smaller pieces
* Don’t log and throw exception
* Do metrics emission in catch exception block
* Don’t log individual log statements if the object has toString
* Don’t do toString in log.info(), log.error(),...etc
* Don’t put class variable in parameter
* Add zero metrics in finally block
* Prevent duplicated code
* More sufficient and more readable log
* More readable variable name {divergedReplicaNumber(x) numberOfDivergedReplicas(o)}, {getCellInfoMapFailure(x), failedToGetCellInfoMap(o))
* Interface and implementation should be in the same folder
* Use String.format as many as possible
* Use time out when we use future get
* Check Typo more carefully
* Don’t return null, be sure use Optional<>
* Don’t put the unrelated stuff into the same commit. However, it’s ok in the same CR
* Don’t do duplicated log like https://code.amazon.com/reviews/CR-10314049/revisions/5#/comments
* Use switch if there are more than 2 branches
* If need to implement public API, send out a short CR for signature in the early stage
* Think refactoring the existing code (duplicated code) if we make change
