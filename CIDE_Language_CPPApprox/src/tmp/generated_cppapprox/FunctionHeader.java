package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionHeader extends GenASTNode {
  public FunctionHeader(ArrayList<Modifier> modifier, FunctionReturnType functionReturnType, FunctionExoticStuff functionExoticStuff, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Modifier>("modifier", modifier),
      new PropertyOne<FunctionReturnType>("functionReturnType", functionReturnType),
      new PropertyZeroOrOne<FunctionExoticStuff>("functionExoticStuff", functionExoticStuff),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public FunctionHeader(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionHeader(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Modifier> getModifier() {
    return ((PropertyZeroOrMore<Modifier>)getProperty("modifier")).getValue();
  }
  public FunctionReturnType getFunctionReturnType() {
    return ((PropertyOne<FunctionReturnType>)getProperty("functionReturnType")).getValue();
  }
  public FunctionExoticStuff getFunctionExoticStuff() {
    return ((PropertyZeroOrOne<FunctionExoticStuff>)getProperty("functionExoticStuff")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
