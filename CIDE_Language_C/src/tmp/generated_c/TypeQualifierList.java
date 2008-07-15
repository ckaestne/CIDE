package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeQualifierList extends GenASTNode {
  public TypeQualifierList(TypeQualifier typeQualifier, ArrayList<TypeQualifier> typeQualifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeQualifier>("typeQualifier", typeQualifier),
      new PropertyZeroOrMore<TypeQualifier>("typeQualifier1", typeQualifier1)
    }, firstToken, lastToken);
  }
  public TypeQualifierList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeQualifierList(cloneProperties(),firstToken,lastToken);
  }
  public TypeQualifier getTypeQualifier() {
    return ((PropertyOne<TypeQualifier>)getProperty("typeQualifier")).getValue();
  }
  public ArrayList<TypeQualifier> getTypeQualifier1() {
    return ((PropertyZeroOrMore<TypeQualifier>)getProperty("typeQualifier1")).getValue();
  }
}
