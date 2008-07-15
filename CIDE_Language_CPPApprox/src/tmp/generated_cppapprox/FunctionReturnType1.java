package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionReturnType1 extends FunctionReturnType {
  public FunctionReturnType1(ASTTextNode text74, ASTTextNode text75, ASTTextNode text76, ASTStringNode identifier, ASTTextNode text77, ASTTextNode text78, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text74", text74),
      new PropertyZeroOrOne<ASTTextNode>("text75", text75),
      new PropertyZeroOrOne<ASTTextNode>("text76", text76),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<ASTTextNode>("text77", text77),
      new PropertyZeroOrOne<ASTTextNode>("text78", text78)
    }, firstToken, lastToken);
  }
  public FunctionReturnType1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionReturnType1(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText74() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text74")).getValue();
  }
  public ASTTextNode getText75() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text75")).getValue();
  }
  public ASTTextNode getText76() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text76")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ASTTextNode getText77() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text77")).getValue();
  }
  public ASTTextNode getText78() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text78")).getValue();
  }
}
