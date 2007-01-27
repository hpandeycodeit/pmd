package net.sourceforge.pmd.ast;

import java.util.List;

import net.sourceforge.pmd.AbstractRuleChainVisitor;
import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.rules.XPathRule;

public class JavaRuleChainVisitor extends AbstractRuleChainVisitor<ASTCompilationUnit> {
    
    protected void indexNodes(List<ASTCompilationUnit> astCompilationUnits, RuleContext ctx) {
        JavaParserVisitor javaParserVistor = new JavaParserVisitorAdapter() {
            // Perform a visitation of the AST to index nodes which need
            // visiting by type
            public Object visit(SimpleJavaNode node, Object data) {
                indexNode(node);
                return super.visit(node, data);
            }
        };

        for (int i = 0; i < astCompilationUnits.size(); i++) {
            javaParserVistor.visit(astCompilationUnits.get(i), ctx);
        }
    }

    protected void visit(Rule rule, SimpleNode node, RuleContext ctx) {
        ((XPathRule) rule).evaluate(node, ctx);
    }
}
