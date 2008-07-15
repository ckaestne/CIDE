package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassConstructor extends GenASTNode {
  public ClassConstructor(Type type, FormalParameterList formalParameterList, ExpressionList expressionList, ArrayList<FieldAssign> fieldAssign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type),
      new PropertyZeroOrOne<FormalParameterList>("formalParameterList", formalParameterList),
      new PropertyZeroOrOne<ExpressionList>("expressionList", expressionList),
      new PropertyZeroOrMore<FieldAssign>("fieldAssign", fieldAssign)
    }, firstToken, lastToken);
  }
  public ClassConstructor(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassConstructor(cloneProperties(),firstToken,lastToken);
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public FormalParameterList getFormalParameterList() {
    return ((PropertyZeroOrOne<FormalParameterList>)getProperty("formalParameterList")).getValue();
  }
  public ExpressionList getExpressionList() {
    return ((PropertyZeroOrOne<ExpressionList>)getProperty("expressionList")).getValue();
  }
  public ArrayList<FieldAssign> getFieldAssign() {
    return ((PropertyZeroOrMore<FieldAssign>)getProperty("fieldAssign")).getValue();
  }
}
