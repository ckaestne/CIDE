package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CPPClass extends GenASTNode {
  public CPPClass(class_head class_head, ArrayList<member> member, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<class_head>("class_head", class_head),
      new PropertyZeroOrMore<member>("member", member)
    }, firstToken, lastToken);
  }
  public CPPClass(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CPPClass(cloneProperties(),firstToken,lastToken);
  }
  public class_head getClass_head() {
    return ((PropertyOne<class_head>)getProperty("class_head")).getValue();
  }
  public ArrayList<member> getMember() {
    return ((PropertyZeroOrMore<member>)getProperty("member")).getValue();
  }
}
