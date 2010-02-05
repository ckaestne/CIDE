package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayDimsAndInits1 extends ArrayDimsAndInits {
  public ArrayDimsAndInits1(Expression expression, ArrayList<Expression> expression1, ArrayList<ASTTextNode> text77, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrMore<Expression>("expression1", expression1),
      new PropertyZeroOrMore<ASTTextNode>("text77", text77)
    }, firstToken, lastToken);
  }
  public ArrayDimsAndInits1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayDimsAndInits1(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ArrayList<Expression> getExpression1() {
    return ((PropertyZeroOrMore<Expression>)getProperty("expression1")).getValue();
  }
  public ArrayList<ASTTextNode> getText77() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text77")).getValue();
  }
}
