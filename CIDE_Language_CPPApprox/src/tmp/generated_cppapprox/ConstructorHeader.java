package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConstructorHeader extends GenASTNode {
  public ConstructorHeader(ArrayList<Modifier> modifier, FunctionExoticStuff functionExoticStuff, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Modifier>("modifier", modifier),
      new PropertyZeroOrOne<FunctionExoticStuff>("functionExoticStuff", functionExoticStuff),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public ConstructorHeader(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConstructorHeader(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Modifier> getModifier() {
    return ((PropertyZeroOrMore<Modifier>)getProperty("modifier")).getValue();
  }
  public FunctionExoticStuff getFunctionExoticStuff() {
    return ((PropertyZeroOrOne<FunctionExoticStuff>)getProperty("functionExoticStuff")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
