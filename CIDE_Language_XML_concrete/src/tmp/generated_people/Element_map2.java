package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_map2 extends Element_map {
  public Element_map2(STag_map sTag_map, ArrayList<CMisc> cMisc1, Content_map_Choice1 content_map_Choice1, ETag_map eTag_map, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_map>("sTag_map", sTag_map),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_map_Choice1>("content_map_Choice1", content_map_Choice1),
      new PropertyOne<ETag_map>("eTag_map", eTag_map),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_map2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_map2(cloneProperties(),firstToken,lastToken);
  }
  public STag_map getSTag_map() {
    return ((PropertyOne<STag_map>)getProperty("sTag_map")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_map_Choice1 getContent_map_Choice1() {
    return ((PropertyOne<Content_map_Choice1>)getProperty("content_map_Choice1")).getValue();
  }
  public ETag_map getETag_map() {
    return ((PropertyOne<ETag_map>)getProperty("eTag_map")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
