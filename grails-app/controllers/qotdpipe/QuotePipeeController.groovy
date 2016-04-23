package qotdpipe

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuotePipeeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QuotePipee.list(params), model:[quotePipeeCount: QuotePipee.count()]
    }

    def show(QuotePipee quotePipee) {
        respond quotePipee
    }

    def create() {
        respond new QuotePipee(params)
    }

    @Transactional
    def save(QuotePipee quotePipee) {
        if (quotePipee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quotePipee.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quotePipee.errors, view:'create'
            return
        }

        quotePipee.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quotePipee.label', default: 'QuotePipee'), quotePipee.id])
                redirect quotePipee
            }
            '*' { respond quotePipee, [status: CREATED] }
        }
    }

    def edit(QuotePipee quotePipee) {
        respond quotePipee
    }

    @Transactional
    def update(QuotePipee quotePipee) {
        if (quotePipee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quotePipee.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quotePipee.errors, view:'edit'
            return
        }

        quotePipee.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quotePipee.label', default: 'QuotePipee'), quotePipee.id])
                redirect quotePipee
            }
            '*'{ respond quotePipee, [status: OK] }
        }
    }

    @Transactional
    def delete(QuotePipee quotePipee) {

        if (quotePipee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        quotePipee.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quotePipee.label', default: 'QuotePipee'), quotePipee.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quotePipee.label', default: 'QuotePipee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
