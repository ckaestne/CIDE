package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_script2 extends Element_script {
  public Element_script2(STag_script sTag_script, ArrayList<CMisc> cMisc1, Content_script_Seq1 content_script_Seq1, ETag_script eTag_script, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_script>("sTag_script", sTag_script),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_script_Seq1>("content_script_Seq1", content_script_Seq1),
      new PropertyOne<ETag_script>("eTag_script", eTag_script),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_script2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_script2(cloneProperties(),firstToken,lastToken);
  }
  public STag_script getSTag_script() {
    return ((PropertyOne<STag_script>)getProperty("sTag_script")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_script_Seq1 getContent_script_Seq1() {
    return ((PropertyOne<Content_script_Seq1>)getProperty("content_script_Seq1")).getValue();
  }
  public ETag_script getETag_script() {
    return ((PropertyOne<ETag_script>)getProperty("eTag_script")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
