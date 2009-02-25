package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassConstructor extends GenASTNode {
  public ClassConstructor(Type type, FormalParameterList formalParameterList, IdentifierList identifierList, ArrayList<FieldAssign> fieldAssign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type),
      new PropertyZeroOrOne<FormalParameterList>("formalParameterList", formalParameterList),
      new PropertyZeroOrOne<IdentifierList>("identifierList", identifierList),
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
  public IdentifierList getIdentifierList() {
    return ((PropertyZeroOrOne<IdentifierList>)getProperty("identifierList")).getValue();
  }
  public ArrayList<FieldAssign> getFieldAssign() {
    return ((PropertyZeroOrMore<FieldAssign>)getProperty("fieldAssign")).getValue();
  }
}
