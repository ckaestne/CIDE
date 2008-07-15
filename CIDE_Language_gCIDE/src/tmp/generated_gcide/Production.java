package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Production extends GenASTNode {
  public Production(ArrayList<Choice> choice, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<Choice>("choice", choice),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public Production(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Production(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Choice> getChoice() {
    return ((PropertyList<Choice>)getProperty("choice")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
