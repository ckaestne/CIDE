package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RuleId2 extends RuleId {
  public RuleId2(ASTStringNode rule_ref, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("rule_ref", rule_ref)
    }, firstToken, lastToken);
  }
  public RuleId2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RuleId2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRule_ref() {
    return ((PropertyOne<ASTStringNode>)getProperty("rule_ref")).getValue();
  }
}
