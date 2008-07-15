package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeParameters extends GenASTNode {
  public TypeParameters(ArrayList<TypeParameter> typeParameter, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<TypeParameter>("typeParameter", typeParameter)
    }, firstToken, lastToken);
  }
  public TypeParameters(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeParameters(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<TypeParameter> getTypeParameter() {
    return ((PropertyList<TypeParameter>)getProperty("typeParameter")).getValue();
  }
}
