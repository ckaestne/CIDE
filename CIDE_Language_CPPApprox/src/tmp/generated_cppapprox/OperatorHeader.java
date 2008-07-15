package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OperatorHeader extends GenASTNode {
  public OperatorHeader(ArrayList<Modifier> modifier, FunctionReturnType functionReturnType, FunctionExoticStuff functionExoticStuff, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Modifier>("modifier", modifier),
      new PropertyOne<FunctionReturnType>("functionReturnType", functionReturnType),
      new PropertyZeroOrOne<FunctionExoticStuff>("functionExoticStuff", functionExoticStuff)
    }, firstToken, lastToken);
  }
  public OperatorHeader(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OperatorHeader(cloneProperties(),firstToken,lastToken);
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
}
