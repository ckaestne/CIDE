package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BaliGrammarRule extends GenASTNode {
  public BaliGrammarRule(ArrayList<Production> production, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<Production>("production", production),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public BaliGrammarRule(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BaliGrammarRule(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Production> getProduction() {
    return ((PropertyList<Production>)getProperty("production")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
