package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Rule extends GenASTNode {
  public Rule(Modifier modifier, RuleId ruleId, ASTTextNode text5, altList altList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Modifier>("modifier", modifier),
      new PropertyOne<RuleId>("ruleId", ruleId),
      new PropertyZeroOrOne<ASTTextNode>("text5", text5),
      new PropertyOne<altList>("altList", altList)
    }, firstToken, lastToken);
  }
  public Rule(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Rule(cloneProperties(),firstToken,lastToken);
  }
  public Modifier getModifier() {
    return ((PropertyZeroOrOne<Modifier>)getProperty("modifier")).getValue();
  }
  public RuleId getRuleId() {
    return ((PropertyOne<RuleId>)getProperty("ruleId")).getValue();
  }
  public ASTTextNode getText5() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text5")).getValue();
  }
  public altList getAltList() {
    return ((PropertyOne<altList>)getProperty("altList")).getValue();
  }
}
