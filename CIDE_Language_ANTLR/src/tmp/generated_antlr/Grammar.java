package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Grammar extends GenASTNode implements ISourceFile {
  public Grammar(RuleId ruleId, ArrayList<Rule> rule, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RuleId>("ruleId", ruleId),
      new PropertyZeroOrMore<Rule>("rule", rule),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public Grammar(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Grammar(cloneProperties(),firstToken,lastToken);
  }
  public RuleId getRuleId() {
    return ((PropertyOne<RuleId>)getProperty("ruleId")).getValue();
  }
  public ArrayList<Rule> getRule() {
    return ((PropertyZeroOrMore<Rule>)getProperty("rule")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
